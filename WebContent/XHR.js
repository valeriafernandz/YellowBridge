var myFile = "";
var r,response,s;

class XHR{
    //dat va a ser el json proveniente del form y url va a ser el servlet al cual se quiere enviar
 login(url,data){
     var x= new XMLHttpRequest();
     x.onreadystatechange=function(){
    if((this.readyState==4)&&(this.status==200)){
    	 r=JSON.parse(x.response);
        console.log(x.responseText);
        console.log(toString(r.status));
        alert(r.msg);
        window.sessionStorage.username=r.username;
		window.sessionStorage.user_id=r.user_id;
		window.sessionStorage.type=r.type_user;
        if(r.url!=null){
        window.top.location.href=r.url;
        }
    }else{
        console.log(x.statusText);
    }
}
    x.open("post",url,true);
    x.setRequestHeader('Content-Type','application/Json');
    x.send(JSON.stringify(data));
}
 get(url){
    var x= new XMLHttpRequest();
     x.onreadystatechange=function(){
	    if((this.readyState==4)&&(this.status==200)){
			 response=JSON.parse(x.response);
			if(r.url){
			window.top.location.href=r.url;
			}
	     }else{
			 if(/\{/.test(x.responseText)){
				  response=JSON.parse(x.response);
			 }
	         console.log(x.responseText);
	     }
 }
     x.open("get",url,true);
     x.send();
 }
 getvideoData(url){
    var x= new XMLHttpRequest();
     x.onreadystatechange=function(){
	    if((this.readyState==4)&&(this.status==200)){
			 response=JSON.parse(x.response);
			if(r.url){
			window.top.location.href=r.url;
			}
	     }else{
			 if(/\{/.test(x.responseText)){
				  response=JSON.parse(x.response);
			 }
	         console.log(x.responseText);
	     }
 }
     x.open("get",url,false);
     x.send();
 }
 up(){
		var x= new XMLHttpRequest();
		var formData = new FormData();
		formData.append("file", $("file").files[0]);
		formData.append("titulovid",$("titulovid").value.trim());
		formData.append("deArchivo",$("deArchivo").value.trim());
		//myFile = $("file").files[0].name;
		x.onreadystatechange = function () {
			if (x.status === 200 && x.readyState === 4) {
				$("uploadStatus").textContent = x.responseText + "\nFile uploaded";
				$("loading").style="visibility:hidden";
			}
		}

		x.open("post", "./SubirArchivo", true);
		x.send(formData);

 }
 like(media_id){
	 var x= new XMLHttpRequest();
	 x.onreadystatechange=function(){
		    if((this.readyState==4)&&(this.status==200)){

		     }else{
		         console.log(x.statusText);
		     }
	 }

x.open("get","/YellowBridge/LikeServlet?media_id="+media_id ,true);
x.send();
}

dislike(media_id){
	 var x= new XMLHttpRequest();
	 x.onreadystatechange=function(){
		    if((this.readyState==4)&&(this.status==200)){

		     }else{
		         console.log(x.statusText);
		     }
	 }

x.open("get","/YellowBridge/DislikeServlet?media_id="+media_id ,true);
x.send();
 }

 comment(media_id,comment){
	 var x= new XMLHttpRequest();
	 var formData= new FormData();
	 formData.append("media_id",media_id);
	 formData.append("comment",comment);
	 x.onreadystatechange=function(){
		    if((this.readyState==4)&&(this.status==200)){

		     }else{
		        	 console.log(x.responseText+"error");
		     }
	 }
	 x.open("post","/YellowBridge/CommentsServlet?media_id="+media_id,true);
	 x.send(formData);
 }
deleteComment(comment_id){
	var x= new XMLHttpRequest();
	
	x.onreadystatechange=function(){
		   if((this.readyState==4)&&(this.status==200)){
			   r= JSON.parse(response);
			   console.log('Borrando comentario');
			   window.top.location.href=r.url;
			}else{
				//console.log(x.responseText+"error");
			}
	}
	x.open("get","/YellowBridge/DeleteComment?comment_id="+comment_id,true);
	x.send();
}
views(media_id){
	var x= new XMLHttpRequest();
	x.onreadystatechange=function(){
		   if((this.readyState==4)&&(this.status==200)){
			   r= JSON.parse(response);
			   window.top.location.href=r.url;
			}else{
				//console.log(x.responseText+"error");
			}
	}
	x.open("get","/YellowBridge/views?media_id="+media_id,true);
	x.send();
}
 list(url){
	 var x=new XMLHttpRequest();
	 x.onreadystatechange=function(){
		 if((this.readyState==4)&&(this.status==200)){
			 console.log(x.responseText);
			 eval("r="+x.responseText);
		       console.log(r);
		 }else{
			 console.log(x.responseText);
		 }
	 }
	 x.open("get",url,false);
	 x.send();
 }
 bio(url){
	 var x=new XMLHttpRequest();
	 x.onreadystatechange=function(){
		 if((this.readyState==4)&&(this.status==200)){
			 console.log(x.responseText);
			 eval("s="+x.responseText);
		       console.log(s);
		 }else{
			 console.log(x.responseText);
		 }
	 }
	 x.open("get",url,false);
	 x.send();
 }
 
 
}
// funcion para crear un boton de logout en todas las paginas (se usa el xhr.js porque se carga en todas las paginas sin embargo
// seria bueno ponerlo en otro js y llamar a ese en todas las paginas pero nose pues
//document.body.onload=buttons();
function buttons(){
	if(window.sessionStorage.type<3){
	var buttonBox = document.getElementById("navbarButtons");
	var channelButton = document.createElement("li");
	var logoutButton = document.createElement("li");
	var channel=`<a href="channel.html" class="btn-flat tooltipped amber-text accent-3" data-position="bottom" data-delay="50" data-tooltip="Channel">
                                    Channel
                                </a>
                                `;
	var logout=`<a href="login.html" class="btn-flat tooltipped" data-position="bottom" data-delay="50" data-tooltip="LogOut" onclick="logout()">
              <i class="material-icons left amber-text accent-3">keyboard_tab</i>
            </a>
    `;
  logoutButton.innerHTML=logout;
  channelButton.innerHTML=channel;
  buttonBox.appendChild(channelButton);
  buttonBox.appendChild(logoutButton);
  console.log("boton creado");
  document.getElementById("login").style="display:none";
  document.getElementById("register").style="display:none";

	}

}
//funcion para que al hacer logout se borre el session Storage(se debe relacionar con el logoutServlet para que sea enserio)
function logout(){

	for (const key in window.sessionStorage) {
		window.sessionStorage.removeItem(key);
		}
		
	console.log("usuario ha cerrado sesion");
	}
	