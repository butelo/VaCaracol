package com.muaki.vaca.caracol;

import org.robovm.cocoatouch.foundation.NSAutoreleasePool;
import org.robovm.cocoatouch.glkit.GLKViewDrawableMultisample;
import org.robovm.cocoatouch.glkit.GLKViewDrawableStencilFormat;
import org.robovm.cocoatouch.uikit.UIApplication;

import com.badlogic.gdx.backends.iosrobovm.IOSApplication;
import com.badlogic.gdx.backends.iosrobovm.IOSApplicationConfiguration;

public class RobovmLauncher extends IOSApplication.Delegate {
	@Override
	protected IOSApplication createApplication() {
		IOSApplicationConfiguration config = new IOSApplicationConfiguration();
		config.orientationLandscape = true;
		config.orientationPortrait = false;
		config.multisample = GLKViewDrawableMultisample.Sample4X;
		config.stencilFormat = GLKViewDrawableStencilFormat.Format8;
		return new IOSApplication(new CowSnail(), config);
	}

	public static void main(String[] argv) {
		NSAutoreleasePool pool = new NSAutoreleasePool();
		UIApplication.main(argv, null, RobovmLauncher.class);
		pool.drain();
	}
}
