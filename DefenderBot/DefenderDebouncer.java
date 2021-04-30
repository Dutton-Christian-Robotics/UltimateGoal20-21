package org.firstinspires.ftc.teamcode.dcs15815;

import com.qualcomm.robotcore.util.ElapsedTime;

import java.util.concurrent.Callable;

// this is code originally written for the 2019-2020 season
public class DefenderDebouncer {
    public long timeout;
    private ElapsedTime timer;
    private Callable codeBlock;
    private boolean isFirstRun = true;

    DefenderDebouncer(long t, Callable<Void> block) {
	   timeout = t;
	   timer = new ElapsedTime(ElapsedTime.Resolution.MILLISECONDS);
	   timer.reset();
	   codeBlock = block;
    }

    public void run() throws Exception {
	   if (isFirstRun || (timer.time() > timeout)) {
		  isFirstRun = false;
		  codeBlock.call();
		  timer.reset();
	   }
    }
}