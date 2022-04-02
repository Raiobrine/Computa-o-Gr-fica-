    window.onload = function(){
        var canvas = document.getElementById("canvasGrafico");
        if (canvas) {
    //altura da canvas e largura = 1000
    var altura = 500, largura = 500;
    //posição horizontal inicial do gráfico
    var x = 0;
    //valor dos pontos do gráfico, que será alterado aleatoriamente
    var valor;

//formatando a canvas
canvas.setAttribute("width", largura);
canvas.setAttribute("height", altura);

//obtendo o contexto 2d
var ctx = canvas.getContext("2d");
ctx.fillStyle = "gray";
ctx.fillRect(0, 0, largura, altura);
ctx.font = "300px Courier";
        }
    function desenharGrafico() {
	//define o avanço horizontal
	x+=5;
	//gera um valor aleatório entre 0 e 100
	valor = parseInt(Math.random() * 100);
	//desenha uma linha até a posição gerada
	ctx.lineTo(x, altura-valor);
	ctx.stroke();
	//desenha um retangulo onde está sendo escrito o valor do gráfico
	ctx.fillStyle = "gray";
	ctx.fillText(valor, x, 30);
}
//Intervalo do desenho
setInterval(desenharGrafico, 150);       
};
