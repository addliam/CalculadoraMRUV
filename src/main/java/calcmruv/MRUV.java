package calcmruv;

/**
 *
 * @author codeli4m
 */

public class MRUV {
    
    double distancia, tiempo, velocidadInicial, velocidadFinal, aceleracion;

    public double getDistancia() {
        return distancia;
    }

    public void setDistancia(double distancia) {
        this.distancia = distancia;
    }

    public double getTiempo() {
        return tiempo;
    }

    public void setTiempo(double tiempo) {
        this.tiempo = tiempo;
    }

    public double getVelocidadInicial() {
        return velocidadInicial;
    }

    public void setVelocidadInicial(double velocidadInicial) {
        this.velocidadInicial = velocidadInicial;
    }

    public double getVelocidadFinal() {
        return velocidadFinal;
    }

    public void setVelocidadFinal(double velocidadFinal) {
        this.velocidadFinal = velocidadFinal;
    }

    public double getAceleracion() {
        return aceleracion;
    }

    public void setAceleracion(double aceleracion) {
        this.aceleracion = aceleracion;
    }
    
    // CALCULO DE VARIABLES
    private double calcularDistanciaConVelocidadInicial(double velocidadInicial, double tiempo, double aceleracion){
        double cDistancia = velocidadInicial*tiempo+0.5*aceleracion*tiempo*tiempo;
        return cDistancia;
    }

        private double calcularDistanciaConVelocidadFinal(double velocidadFinal, double tiempo, double aceleracion){
        double cDistancia = velocidadFinal*tiempo-0.5*aceleracion*tiempo*tiempo;
        return cDistancia;
    }
    
    // FORMULAS
    public double[] getDistanciaYVelocidadInicial(){
        double[] resultado = new double[2];
        double distancia = calcularDistanciaConVelocidadFinal(velocidadFinal, tiempo, aceleracion);
        double velocidadInicial = velocidadFinal - aceleracion * tiempo;
        resultado[0] = distancia;
        resultado[1] = velocidadInicial;
        return resultado;
    }    
    
    public double[] getDistanciaYVelocidadFinal(){
        double[] resultado = new double[2];
        double distancia = calcularDistanciaConVelocidadInicial(velocidadInicial, tiempo, aceleracion);
        double velocidadFinal = velocidadInicial + aceleracion * tiempo;
        resultado[0] = distancia;
        resultado[1] = velocidadFinal;
        return resultado;
    }

    public double[] getDistanciaYTiempo(){
        double[] resultado = new double[2];
        double tiempo = (velocidadFinal-velocidadInicial)/aceleracion;
        double distancia = calcularDistanciaConVelocidadInicial(velocidadInicial, tiempo, aceleracion);
        resultado[0] = distancia;
        resultado[1] = tiempo;
        return resultado;
    }
    public double[] getDistanciaYAceleracion(){
        double[] resultado = new double[2];
        double aceleracion = (velocidadFinal-velocidadInicial)/tiempo;
        double distancia = calcularDistanciaConVelocidadInicial(velocidadInicial, tiempo, aceleracion);
        resultado[0] = distancia;
        resultado[1] = aceleracion;
        return resultado;
    }
    // PARA VELOCIDAD INICIAL
    public double[] getVelocidadInicialYVelocidadFinal(){
        double[] resultado = new double[2];
        double velocidadInicial = (distancia-(aceleracion*tiempo*tiempo/2))/tiempo;
        double velocidadFinal = tiempo*aceleracion+velocidadInicial;      
        resultado[0] = velocidadInicial;
        resultado[1] = velocidadFinal;
        return resultado;
    }
    public double[] getVelocidadInicialYTiempo(){
        double[] resultado = new double[2];
        double prevCalculo = (velocidadFinal*velocidadFinal)-(2*aceleracion*distancia);
        // antes de sacar raiz cuadrada asegurarse que sea positivo
        prevCalculo = (prevCalculo<0) ? (prevCalculo*-1) : prevCalculo; 
        double velocidadInicial = Math.sqrt(prevCalculo);
        double tiempo = (velocidadFinal - velocidadInicial)/aceleracion;
        resultado[0] = velocidadInicial;
        resultado[1] = tiempo;
        return resultado;
    }
    public double[] getVelocidadInicialYAceleracion(){
        double[] resultado = new double[2];
        double velocidadInicial = (2*distancia/tiempo)-velocidadFinal;
        double aceleracion = (velocidadFinal-velocidadInicial)/tiempo;
        resultado[0] = velocidadInicial;
        resultado[1] = aceleracion;
        return resultado;
    }
    // VELOCIDAD FINAL
    public double[] getVelocidadFinalYTiempo(){
        double[] resultado = new double[2];
        double prevCalculo = (velocidadInicial*velocidadInicial) + (2*distancia*aceleracion);
        System.out.println(prevCalculo);
        double velocidadFinal = Math.sqrt(prevCalculo);
        double tiempo = (velocidadFinal - velocidadInicial)/aceleracion;
        resultado[0] = velocidadFinal;
        resultado[1] = tiempo;
        return resultado;
    }
    public double[] getVelocidadFinalYAceleracion(){
        double[] resultado = new double[2];
        double velocidadFinal = (2*distancia/tiempo)-velocidadInicial;
        double aceleracion = (2*(distancia-(velocidadInicial*tiempo)))/(tiempo*tiempo);
        resultado[0] = velocidadFinal;
        resultado[1] = aceleracion;
        return resultado;
    }
    // ACELERACION
    public double[] getTiempoYAceleracion(){
        double[] resultado = new double[2];
        double tiempo = distancia * (2/(velocidadInicial+velocidadFinal));
        double aceleracion = ((velocidadFinal*velocidadFinal)-(velocidadInicial*velocidadInicial))/(2*distancia);
        resultado[0] = tiempo;
        resultado[1] = aceleracion;
        return resultado;
    }    
    
    public String resolve(){
        String txt = "";
        if ((this.distancia==0.0) && (this.velocidadFinal==0.0)){
            double[] res = this.getDistanciaYVelocidadFinal();
            txt+="Distancia: "+res[0]+"\n";
            txt+="Velocidad Final: "+res[1];
        }
        else if ((this.distancia==0.0) && (this.velocidadInicial==0.0)){
            double[] res = this.getDistanciaYVelocidadInicial();
            txt+="Distancia: "+res[0]+"\n";
            txt+="Velocidad Final: "+res[1];
        }
        else if ((this.distancia==0.0) && (this.tiempo==0.0)){
            double[] res = this.getDistanciaYTiempo();
            txt+="Distancia: "+res[0]+"\n";
            txt+="Tiempo: "+res[1];
    }
        else if ((this.distancia==0.0) && (this.aceleracion==0.0)){
            double[] res = this.getDistanciaYAceleracion();
            txt+="Distancia: "+res[0]+"\n";
            txt+="Aceleracion: "+res[1];
        }
        else if ((this.velocidadInicial==0.0) && (this.velocidadFinal==0.0)){
            double[] res = this.getVelocidadInicialYVelocidadFinal();
            txt+="Velocidad inicial: "+res[0]+"\n";
            txt+="Velocidad final: "+res[1];
        }      
        else if ((this.velocidadInicial==0.0) && (this.tiempo==0.0)){
            double[] res = this.getVelocidadInicialYTiempo();
            txt+="Velocidad inicial: "+res[0]+"\n";
            txt+="Tiempo: "+res[1];
        }       
        else if ((this.velocidadInicial==0.0) && (this.aceleracion==0.0)){
            double[] res = this.getVelocidadInicialYAceleracion();
            txt+="Velocidad inicial: "+res[0]+"\n";
            txt+="Aceleracion: "+res[1];
        }     
        else if ((this.velocidadFinal==0.0) && (this.tiempo==0.0)){
            double[] res = this.getVelocidadFinalYTiempo();
            txt+="Velocidad final: "+res[0]+"\n";
            txt+="Tiempo: "+res[1];
        }          
        else if ((this.velocidadFinal==0.0) && (this.aceleracion==0.0)){
            double[] res = this.getVelocidadFinalYAceleracion();
            txt+="Velocidad final: "+res[0]+"\n";
            txt+="Aceleracion: "+res[1];
        }
        else if ((this.tiempo==0.0) && (this.aceleracion==0.0)){
            double[] res = this.getTiempoYAceleracion();
            txt+="Tiempo: "+res[0]+"\n";
            txt+="Aceleracion: "+res[1];
        }    
        else{
            System.out.println("None");
        }
        return txt;
    }
}
