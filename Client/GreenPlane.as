package  {
	     import flash.display.MovieClip;
         public class GreenPlane extends MovieClip
         {
            public function GreenPlane(x:int,y:int)
            {
	            _x=x;
	            _y=y;
            }
            public function getArea():Number
            {
                 // The formula is Pi times the radius squared.
                 return Math.PI * Math.pow((width / 2), 2);
            }
            public function getCircumference():Number
            {
// The formula is Pi times the diameter.
            return Math.PI * width;
            }
         }
		}
	
	
	
	
	
	
	
	
	

	