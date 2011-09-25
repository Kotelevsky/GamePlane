package  {
	
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
	
	
	public class main extends MovieClip {
		private var flag: Boolean = true;
		private var i: int = 0;
		private var timer:Timer = new Timer(50, 0);
		private var socket: Socket;
		
		public function main() {
			// constructor code
			try{
				Security.loadPolicyFile("xmlsocket://localhost:5050");
			}catch(e: Event){
				ObjText.text = e.type;
			}
			socket = new Socket("localhost",5000);
			socket.addEventListener(Event.CONNECT, onConnectHandle);
			socket.addEventListener(ProgressEvent.SOCKET_DATA, onDataHandle);
			
			
			but_run.addEventListener(MouseEvent.CLICK, onClick);
		}
		
		public function onClick(e: MouseEvent): void{
			
			if(flag){
				
				count.text = "";
				ObjText.text = "";
				flag = false;
				timer.addEventListener(TimerEvent.TIMER, onTimer);
				timer.start();
			}else{
				i = 0;
				flag = true;
				timer.stop();
				socket.close();
			}
		}
		
		public function onTimer(e: TimerEvent): void{
			i++;
			ObjText.text += e.toString() + " ";
			count.text = i.toString();
			socket.writeUTFBytes("hsfjfsdhfjsdjfsdjfjshdfjshfjsjfh\n");
			
			socket.flush();
		}
		
		public function onConnectHandle(e: Event): void{
			ObjText.text = "Connect-----------------";
		}
		
		public function onDataHandle(e: ProgressEvent): void{
			//var text:String = socket.readUTF();
			ObjText.text += this.socket.readUTFBytes(this.socket.bytesAvailable)+ " ";
		}
	}
	
}
