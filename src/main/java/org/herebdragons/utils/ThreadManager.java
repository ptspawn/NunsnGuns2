package org.herebdragons.utils;


import java.util.LinkedList;
import java.util.List;

public class ThreadManager extends ThreadGroup {

    private boolean isAlive;
    private List<Runnable> taskList;
    private static final IDAssigner idAssigner = new IDAssigner(1);

    public ThreadManager(int numThreads) {
        super(Strings.GAME_NAME + " - Thread Manager");
        super.setDaemon(true);
        taskList = new LinkedList<Runnable>();
        isAlive = true;

        for (int i = 0; i < numThreads; i++) {
            Logger.log("Created a new Thread - " + Strings.GAME_NAME + " PooledThread ");
            new PooledThread(Strings.GAME_NAME + " PooledThread ", this).start();

        }
    }

    public synchronized void runTask(Runnable task) {
        if (!isAlive)
            throw new IllegalStateException("ThreadPool " + idAssigner.getCurrentID() + "is dead");

        if (task != null) {
            taskList.add(task);
            notify();

        }
    }

    public synchronized void close() {

        Logger.log("Closing Thread Manager");

        if (!isAlive)
            return;

        isAlive = false;

        taskList.clear();
        interrupt();
    }

    public void join() {

        Logger.log("Joining all Threads from " + this);

        synchronized (this) {
            isAlive = false;
            notifyAll();
        }

        Thread[] threads = new Thread[activeCount()];
        int count = enumerate(threads);

        for (int i = 0; i < count; i++) {

            try {
                threads[i].join();
            } catch (InterruptedException e) {
                Logger.err("Interrupted task " + this);
            }
        }
    }

    private synchronized Runnable getTask() throws InterruptedException {
        Logger.log("Aqquiring task");

        while (taskList.size() == 0) {
            if (!isAlive) {
                Logger.log("ThreadPool is dead");
                return null;
            }

            Logger.log("Waiting for tasks");
            wait();
        }

        Logger.log("Done waiting");
        return taskList.remove(0);
    }


    private class PooledThread extends Thread {
        private ThreadManager pool;

        private PooledThread(String name, ThreadManager pool) {
            super(pool, name + " PooledThread-" + idAssigner.next());
            this.pool = pool;
            Logger.log("A PooledThread was created " + this.getName());
        }

        @Override
        public void run() {

            Logger.log("Run Method from " + this.getName() + " was called");

            while (!isInterrupted()) {
                Runnable task = null;
                try {

                    Logger.log("Trying to get a task");
                    task = pool.getTask();

                    Logger.log(this.getName() + " now has a task");

                } catch (InterruptedException e) {
                    Logger.err("Interrupted task " + this);
                }

                if (task == null) {
                    Logger.err("The task was null");
                    return;
                }

                try {

                    Logger.log(this + " - Trying to tun its task - " + task);
                    task.run();
                    Logger.log(this + " - finished its task - " + task);

                } catch (Throwable t) {
                    Logger.err("Caught a Throwable \n" + t.getMessage());
                    pool.uncaughtException(this, t);
                }
            }
        }

        @Override
        public String toString() {
            return "PooledThread  " + this.getName();
        }
    }

    private static class IDAssigner {

        private int baseID;

        private IDAssigner(int baseID) {
            this.baseID = baseID;
        }

        private int next() {
            return baseID++;
        }

        private int getCurrentID() {
            return baseID;
        }
    }


}
