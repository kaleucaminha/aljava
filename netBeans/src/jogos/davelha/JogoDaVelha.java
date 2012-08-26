package jogos.davelha;

import aljava.Alj;

public class JogoDaVelha {

    boolean vezDoJogador1;
    Casinha[] casas;

    String jogador1;
    String jogador2;

    public JogoDaVelha() {
        carregaSons();

        Alj.tela.tamanho(300, 340);
        vezDoJogador1 = true;

        jogador1 = Alj.tela.solicitaTexto("Informe seu nome jogador 1.");
        jogador2 = Alj.tela.solicitaTexto("Informe seu nome jogador 2.");
        
        casas = new Casinha[9];
        casas[0] = new Casinha(0, 41);
        casas[1] = new Casinha(101, 41);
        casas[2] = new Casinha(201, 41);

        casas[3] = new Casinha(0, 141);
        casas[4] = new Casinha(101, 141);
        casas[5] = new Casinha(201, 141);

        casas[6] = new Casinha(0, 241);
        casas[7] = new Casinha(101, 241);
        casas[8] = new Casinha(201, 241);

        loopJogo();
    }

    void carregaSons(){
        Alj.desenha.texto(20, 200, "Carregando sons, aguarde alguns segundos.", 18);
        Alj.tela.exibe();

        Alj.som.carrega("tick", "sons/tick.wav");
    }

    void loopJogo() {
        while (true) {
            verificaVitoria();
            clickCasinhas();
            desenha();
            Alj.tela.exibe();
        }
    }

    public void clickCasinhas() {
        for (int i = 0; i < 9; i++) {
            if (casas[i].estaOcupada()) {
                continue; //Isso pula para o próximo loop
            }

            //Se o mose está na casinha e o botao esquerdo pressionado, ocupa casa
            if (casas[i].mouseDentro() && Alj.mouse.clickE()) {
                ocupaCasa( casas[i] );
                Alj.som.toca("tick");
            }
        }
    }

    public void ocupaCasa(Casinha casinha){
        if (vezDoJogador1) {
            casinha.ocupaPlayer1();
            vezDoJogador1 = false;
        } else {
            casinha.ocupaPlayer2();
            vezDoJogador1 = true;
        }
    }

    public void verificaVitoria(){
        //Vitória 1a Linha Horizontal
        if(casas[0].estaOcupadaPlayer1() && casas[1].estaOcupadaPlayer1() && casas[2].estaOcupadaPlayer1()){
            vitoriaPlayer1();
        }

        if(casas[0].estaOcupadaPlayer2() && casas[1].estaOcupadaPlayer2() && casas[2].estaOcupadaPlayer2()){
            vitoriaPlayer2();
        }

        //Vitória 2a Linha Horizontal
        if(casas[3].estaOcupadaPlayer1() && casas[4].estaOcupadaPlayer1() && casas[5].estaOcupadaPlayer1()){
            vitoriaPlayer1();
        }

        if(casas[3].estaOcupadaPlayer2() && casas[4].estaOcupadaPlayer2() && casas[5].estaOcupadaPlayer2()){
            vitoriaPlayer2();
        }
    }

    public void vitoriaPlayer1(){
        Alj.tela.exibeMensagem("Parabéns "+jogador1+". Você venceu.");
        Alj.tela.finaliza();
    }

    public void vitoriaPlayer2(){
        Alj.tela.exibeMensagem("Parabéns "+jogador2+". Você venceu.");
        Alj.tela.finaliza();
    }

    public void desenha() {
        desenhaTopo();
        desenhaCasinhas();
    }

    void desenhaTopo(){
        //Barra
        Alj.cor.nome("preto");
        Alj.desenha.retangulo(0, 0, 300, 40);

        //Nome do jogador da vez
        Alj.cor.nome("branco");
        if (vezDoJogador1) {
            Alj.desenha.texto(20, 25, "Sua vez "+jogador1, 14);
        } else {
            Alj.desenha.texto(20, 25, "Sua vez "+jogador2, 14);
        }
    }

    void desenhaCasinhas(){
         //Casinhas coloridas
        for (int i = 0; i < 9; i++) {
            casas[i].desenha();
        }

        //Linhas para demarcar o tabuleiro
        Alj.cor.nome("preto");
        Alj.desenha.linha(100, 40, 100, 340);
        Alj.desenha.linha(200, 40, 200, 340);
        Alj.desenha.linha(0, 140, 300, 140);
        Alj.desenha.linha(0, 240, 300, 240);
    }
}
