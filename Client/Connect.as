package   {
	import flash.system.Security;
	import flash.net.Socket;
	import flash.events.Event;
	import flash.events.ProgressEvent;
	import com.adobe.serialization.json.JSON;
	import flash.utils.getTimer;
	import com.adobe.serialization.json.JSONParseError;
	
	
	public class Connect {
		private var socket:Socket;
		private var plane: Array;
		public var idUser: int = -1;
		public var idRoom: int = -1;
		private var rooms: Array;
		private var closeGame: int = 0;
		private var addr: String = "127.0.0.1";
		private var userName: String = "Player1";
		// когда игра только стартовала
		
		private var curX: int;
		private var curY: int;
		private var moveX: int;
		private var moveY: int;
		
		public function setUserName(name: String): void {
			this.userName = name;
		}
		
		public function getUserName(): String {
			return this.userName;
		}
		
		public function getCurX(): int {
			return curX;
		}
		public function getCurY(): int {
			return curY;
		}
		public function getMoveX(): int {
			return moveX;
		}
		public function getMoveY(): int {
			return moveY;
		}
		
		
		public function getPlane(): Array {
			return this.plane;
		}
		
		public function getRooms(): Array {
			return this.rooms;
		}
		
		public function getIdUser(): int {
			return this.idUser;
		}
		
		public function getIdRoom(): int {
			return this.idRoom;
		}
		
		public function isGame(): int {
			return this.closeGame;
		}
		
		public function Disconect(): void {
			this.socket.close();
		}
		
		
		
		
		public function Connect() {
			// constructor code
			//Security.loadPolicyFile("xmlsocket://"+addr+":843");
		}
		
		public function setAddr(addr: String): void {
			this.addr = addr;
		}
		
		public function getAddr(): String {
			return addr;
		}
		
		public function securityStart(): void {
			Security.loadPolicyFile("xmlsocket://"+addr+":843");
		}
		
		public function StartSocket(): void{
			socket = new Socket(addr, 5000);
			socket.addEventListener(Event.CONNECT, onConnectionHandle);
			socket.addEventListener(ProgressEvent.SOCKET_DATA, onDataHandle);
		}
		
		private function onConnectionHandle(e: Event): void {
			//trace(e);
		}
		
		//парсеры строк методы
		private function jsonConnectUser(json: Object):void {
			idUser = json.id_user;
			trace("id user = " + idUser);
			//this.idUser = idUser;
		}
		
		//пакет от сервера, когда клиента зашел превый раз в комнату
		private function jsonRoom(json: Object): void {
			idUser = json.id_plane;
			trace("id user = " + idUser);
			//координаты самолета
			curX = json.coordinate.x;
			curY = json.coordinate.y;
			trace("coordinate = ("+curX+","+curY+")");
			// координаты вектора перемещения
			moveX = json.move.x;
			moveY = json.move.y;
			trace("move = ("+moveX+","+moveY+")");
			// номер комнаты
			idRoom = json.room;
			trace("id room = " + idRoom);
			
			this.curX = curX;
			this.curX = curY;
			this.moveX = moveX;
			this.moveY = moveY;
			
		}
		// пакет выводит все комнаты
		private function jsonAllRoom(json: Object):void {
			var rooms: Array = json.room;
			for (var key:Object in rooms) {
				trace("================================");
                trace("id room = " + rooms[key].id_room);
				trace("name = " + rooms[key].name);
				trace("id count = " + rooms[key].count);
				trace("curent users = " + rooms[key].curent);
            }
			if(rooms.length != 0) this.rooms = rooms;
		}
		
		private function jsonActionClient(json: Object):void{
			var plane: Array = json.plane;
			for(var key:Object in plane) {
				trace("================================");
				trace("coordinate = ("+plane[key].coordinate.x+","+plane[key].coordinate.y+")");
				trace("move = ("+plane[key].move.x+","+plane[key].move.y+")");
				trace("flag = " + plane[key].flag);
				trace("id plane = " + plane[key].id_plane);
			}
			this.plane = plane;
			this.curX = plane[0].coordinate.x;
			this.curY = plane[0].coordinate.y;
		}
		
		private function jsonExit():void {
			this.closeGame = 1;
		}
		
		private function onDataHandle(e: ProgressEvent):void {
			//read message
			var packet:String = socket.readUTFBytes(this.socket.bytesAvailable);
			//trace(packet);
			try{
			var obj: Object = JSON.decode(packet);
			//trace(obj.packet);
			switch (obj.packet) {
				case 'connect_user':
					trace("connect_user");
					jsonConnectUser(obj);
					break;
				case 'room':
					trace("room");
					jsonRoom(obj);
					break;
				case 'action_client':
					trace("action_client");
					jsonActionClient(obj);
					break;
				case 'exit_client':
					trace("exit_client");
					break;
				case 'all_room':
					trace("all_room");
					jsonAllRoom(obj);
					break;
			}
			} catch(e: JSONParseError) {
				trace("wrong");
			}
			
		}
		
		public function sendPacket(packet: String): void {
			trace(packet);
			socket.writeUTFBytes(packet +"\n");
			socket.flush();
		}
		
		// Connect user
		public function connectUser():void {
			var jsonPacket: String = "{\"packet\":\"connect_user\",\"name\":\""+ userName +"\"}";
			sendPacket(jsonPacket);
		}
		
		public function createRoom(nameRoom: String, countPlay: int, idUser: int):void {
			var jsonPacket: String = "{\"packet\":\"create_room\",\"name\": \""+nameRoom+"\", \"id_user\":"+idUser+", \"count\":"+countPlay+"}";
			sendPacket(jsonPacket);
		}
		
		public function connectRoom(idRoom: int, idUser: int):void {
			var jsonPacket: String = "{\"packet\":\"connect_room\", \"id_user\":"+idUser+", \"id_room\":"+idRoom+"}";
			sendPacket(jsonPacket);
		}
		
		public function actionServer(idUser: int, idRoom: int, action: int):void {
			var jsonPacket: String = "{\"packet\":\"action_server\", \"id_user\":"+idUser+", \"id_room\":"+idRoom+",\"action\""+action+"}";
			sendPacket(jsonPacket);
		}
		
		public function exitClient(idUser: int):void {
			var jsonPacket: String = "{\"packet\":\"exit_client\", \"id_user\":"+idUser+"}";
			sendPacket(jsonPacket);
		}
		public function allRoom():void {
			var jsonPacket: String = "{\"packet\":\"all_room\"}";
			sendPacket(jsonPacket);
		}
	}
	
}
