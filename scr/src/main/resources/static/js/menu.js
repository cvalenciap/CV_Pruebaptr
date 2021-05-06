agregarMenu();

function agregarMenu(){
	
    var linkMenu = contexto+"menu/extraerMenu";
	$.ajax({
        url : linkMenu,
        type : "POST",
        dataType : "json",
        async:false
    }).done(function(respuestaMenu) {
    	if(respuestaMenu.estadoRespuesta ==  ConstanteServices.OK ){	   
        	var menuHtml = respuestaMenu.parametros.htmlMenu;
        	$("#menuNavegacion").html(menuHtml);
        } 
    }).fail(function(jqXHR, textStatus, errorThrown) {
    	validarFinDeSesion(jqXHR, textStatus, errorThrown, ConstanteServices.MENSAJE_SESION_EXPIRADA);
    });	
   
}

$('[data-toggle="tooltip"]').tooltip();

function invocarMenu(){
	$("#btnShowMenu").click();
}


var generales = function(){
    $('ul.list-actividad').find('li:nth-child(even)').addClass('interlineado');
    $('table.table').find('tbody tr:nth-child(odd)').addClass('alternative-item');
};


var MenuPanel = function(){

    $('body').on('click', '.btn-show', function () {  
        $('.wrap-ui').addClass('opacity-capa');  
        $('.menu').animate({
            left: '0'
        });
    });

    $('body').on('click', '.btn-hide', function () {
        $('.wrap-ui').removeClass('opacity-capa');
        $('.menu').animate({
            left: '-320'
        });

    });

    $('body').on('click', '.remove-menu-panel', function(){
        $('.btn-hide').trigger('click');
    });

    $('body').on('click', '.box-opacity', function(){
        $('.btn-hide').trigger('click');
    });    

}
MenuPanel();

var nav = function(){

    var $wrap = $('.nav-wrap'),        
    $btn = $wrap.find('li.nav-01 > span'),
    $content = $wrap.find('ul.nav-collapse');

    $('li.nav-01 > span').on('click', function () {
        var $el = $(this),
            $elContent = $el.next('ul.nav-collapse');

        if(!$el.hasClass('active')){
            $content.slideUp();
            $btn.removeClass('active');
            $el.addClass('active');                             
            $elContent.slideDown();                
        }else{
            $el.removeClass('active'); 
            $elContent.slideUp();
        }
    });
} 

nav();

var navSub = function(){

    var $wrap = $('.nav-collapse'),        
    $btn = $wrap.find('li.nav-02 > a'),
    $content = $wrap.find('ul.nav-collapse-sub');

    $('li.nav-02 > a').on('click', function () {
        var $el = $(this),
            $elContent = $el.next('ul.nav-collapse-sub');

        if(!$el.hasClass('activeSub')){
            $content.slideUp();
            $btn.removeClass('activeSub');
            $el.addClass('activeSub');                             
            $elContent.slideDown();                
        }else{
            $el.removeClass('activeSub'); 
            $elContent.slideUp();
        }
    });
}   

navSub();