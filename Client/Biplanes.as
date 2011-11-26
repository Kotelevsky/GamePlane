package Biplanes{
	import flash.display.MovieClip;
	import fl.motion.MotionEvent;
	import flash.events.MouseEvent;
	import flash.utils.Timer;
	import flash.events.TimerEvent;
	import flash.net.Socket;
	import flash.events.Event;
	import flash.events.ProgressEvent;
	import flash.system.Security;
	import flash.net.URLRequest;
	import flash.net.URLLoader;

	public class Game extends MovieClip {
		public 




		public function keyClick(e: KeyboardEvent):void {
			trace("ok");
			switch (e.keyCode) {
				case Keyboard.LEFT :
					flag="l";
					break;
				case Keyboard.RIGHT :
					flag="r";
					break;
				case Keyboard.UP :
					flag="u";
					break;
				case Keyboard.DOWN :
					flag="d";
					break;
			}
		}


	}

}