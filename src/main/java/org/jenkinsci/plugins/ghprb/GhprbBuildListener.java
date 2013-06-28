package org.jenkinsci.plugins.ghprb;

import java.util.logging.Level;
import java.util.logging.Logger;

import hudson.Extension;
import hudson.model.AbstractBuild;
import hudson.model.TaskListener;
import hudson.model.listeners.RunListener;

/**
 *
 * @author janinko
 */
@Extension
public class GhprbBuildListener extends RunListener<AbstractBuild>{

	private static final Logger logger = Logger.getLogger(GhprbBuildListener.class.getName());
	
	@Override
	public void onStarted(AbstractBuild build, TaskListener listener) {
		GhprbTrigger trigger = GhprbTrigger.getTrigger(build.getProject());
		if(trigger == null) return;

		trigger.getGhprb().getBuilds().onStarted(build);
	}
	
	@Override
	public void onCompleted(AbstractBuild build, TaskListener listener) {
		GhprbTrigger trigger = GhprbTrigger.getTrigger(build.getProject());
		
		logger.log(Level.INFO, "Pull request builder: build was completed; trigger:{0}", new Object[]{trigger});
		if(trigger == null) return;

		trigger.getGhprb().getBuilds().onCompleted(build);
	}
}
