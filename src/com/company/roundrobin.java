package com.company;

import java.util.*;

public class roundrobin {

    int timeQuantum;
    int processesNum;
    int contextSwitch;
    float avgWaiting = 0;
    float avgTurnAround = 0;
    process p = null;
    List<process> processes = new ArrayList<>();
    Queue<process> execute = new LinkedList<>();

    public void sort() {
        processes.sort(Comparator.comparing(process::getArrivalTime));
    }

    public void calcTurnAround()
    {
        for (process process : processes)
            process.turnAroundTime += process.waitingTime + process.extime;
    }

    public void calcAvgWaiting()
    {
        for (process process : processes)
            avgWaiting += process.waitingTime;

        avgWaiting = avgWaiting / processes.size();
    }

    public void calcAvgTurnAround()
    {
        for (process process : processes)
            avgTurnAround += process.turnAroundTime;

        avgTurnAround = avgTurnAround / processes.size();
    }

    public boolean areExecuted()
    {
        boolean isFinished = true;
        for (process process : processes) {
            if (process.ExecutionTime != 0) {
                isFinished = false;
                break;
            }
        }
        return isFinished;
    }

    public void checkExecutions(int clock)
    {
        for (process process : processes) {
            if (!(process.getArrivalTime() > clock) && !process.isDone) {
                process.waitingTime = clock - process.getArrivalTime();
                execute.add(process);
                process.isDone = true;
            }
        }
    }

    public void intoTheQueue(process current)
    {
        if (processes.contains(current))
            execute.add(processes.get(processes.indexOf(current)));
    }

    public void addWait(int time, int context)
    {
        for (process process : processes) {
            if (execute.contains(process))
                process.waitingTime += time + context;
        }
    }

    public int calculations( int quantum, int context, int clock)
    {

        process current  = null;
        while(!areExecuted())
        {
            checkExecutions(clock);
            if(current != null)
            {
                intoTheQueue(current);
                current = null;
            }
            if(!execute.isEmpty())
            {
                execute.peek().setStartingTime(clock);
                if(execute.peek().ExecutionTime > quantum)
                {
                    clock += quantum;
                    current = execute.peek();
                    execute.peek().ExecutionTime -= quantum;
                    processes.get(processes.indexOf(execute.peek())).turnAroundTime += context;
                    execute.poll();
                    addWait(quantum, context);
                }
                else
                {
                    int tempExecution = execute.peek().ExecutionTime;
                    clock+= execute.peek().ExecutionTime;
                    execute.peek().ExecutionTime = 0;
                    processes.get(processes.indexOf(execute.peek())).turnAroundTime += context;
                    execute.poll();
                    addWait(tempExecution, context);
                }
                clock += context;
            }
            else
                clock++;
        }
       calcTurnAround();
       calcAvgWaiting();
       calcAvgTurnAround();
       return clock;
    }
}
