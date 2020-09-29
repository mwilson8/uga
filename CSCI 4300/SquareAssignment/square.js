
function randomColor() {
    
    var letters = '0123456789ABCDEF', 
        color = '#';
    for (var i = 0; i < 6; i++ ) {
        color += letters[Math.floor(Math.random() * 16)];
    }
 
    return color;
}

function makeSquare(){

    var height = $('#squareArea').height();
    var width = $('#squareArea').width(); 
    var color = randomColor();
    var $newDiv = $('<div></div>');
    $newDiv.css( 'background-color', color ); 
     var size = Math.floor(Math.random() * 150); //get a height/width less than container height
     var posx = Math.floor(Math.random() * width);
     var posy = Math.floor(Math.random() * height);

    
    $newDiv.css({
        'left':posx+'px',
        'top':posy+'px',
        'height': size+'px',
        'width':size+'px',
        'position':'absolute'
    });
    
    
    $("#squareArea > div").click(function(){ $(this).css('z-index', 99); });
    
    $("#squareArea > div").dblclick(function(){ $(this).remove(); });
        
    if(size + posy < height && size + posx < width)
    $('#squareArea').append( $newDiv );
    
    else makeSquare();

};

function fillArea(){  
    for (var i =0; i < 30; i++) makeSquare();       
}

function changeColor(){
    
    $('#squareArea > div').each(function(){ $(this).css( 'background-color', randomColor() );}); 
}

$( function() {
    var handle = $( "#custom-handle" );
    $( "#slider" ).slider({
      create: function() {
        handle.text( $( this ).slider( "value" ) );
      },
      slide: function( event, ui ) {
        handle.text( ui.value );
      }
    });
  } );

function addSquares(){
    
    var selection = $( "#slider" ).slider( "value" );
    
    for (var i =0; i < selection; i++) makeSquare();
    $( "#slider" ).slider( "value", 0);
}

