import java.util.Random;
import java.util.Arrays;

public class Neurona {
    
    private Ejemplo TS;
    private double[] W;
    private double[] G;
    private double sumaErr;
    private int numErr;
    private double FACTOR_INERCIA;
    private final double APRENDIZAJE = 0.001;
    private boolean use_bp;
    private double bp_value;
    
    public Neurona(int n){
        TS = new Ejemplo(n);
        W = new double[n+1];
        G = new double[n+1];
        Random r = new Random();
        FACTOR_INERCIA = 0;
        for (int i = 0; i < W.length; i++){
            W[i] = ((r.nextDouble() + 0.1) *
                    (r.nextBoolean() ? -1 : 1));
            G[i] = 0;
        }
        use_bp = false;
        bp_value = 0;
        sumaErr = 0;
        numErr = 0;
    }

    public void setInercia(double x){
        FACTOR_INERCIA = x;
    }
    
    public Ejemplo getEjemplo(){
        return TS;
    }
    
    public void correr(){
        TS.output = activacion(propagacion());
    }

    public boolean probar(){
        return (TS.output > 0.5);
    }

    public void set_diff (double bp){
        use_bp = true;
        bp_value = bp;
    }

    private double get_diff(){
        if (use_bp){
            //use_bp = false;
            return bp_value;
        }
        return TS.expected - TS.output;
    }

    public void aprender(){
        double diff = get_diff();
        
        sumaErr += diff*diff;
        numErr += 1;
        if (error(diff)){
            gradientes(diff);
        }
        else{
            System.out.print (Arrays.toString(TS.inputs) + "OK!  ");
        }
    }

    public double[] backPropagation(){
        double[] bp = new double[getEjemplo().inputs.length];
        
        for (int i = 0; i < bp.length; i++){
            bp[i] = get_diff() * W[i+1];
        }
        return bp;
    }

    private double inercia(double x){
        return FACTOR_INERCIA * x;
    }

    public double actualizar(){
        for (int i = 0; i < W.length; i++){
            W[i] += G[i];
            G[i] = inercia(G[i]);
        }
        // Calcula error cuadratico medio
        double eqm = sumaErr /numErr;
        sumaErr = 0;
        numErr = 0;
        return eqm;
    }
    
    private static double[] oldG;

    private static double[] aceleracion;

    private void gradientes(double err){
        if (oldG == null) oldG = new double[G.length];
        if (aceleracion == null) aceleracion  = new double[G.length];
        double g0 = APRENDIZAJE * err;
        if(g0*oldG[0] > 0){
            aceleracion[0] *= 1.05;
        }
        else {
            aceleracion[0] = 1;
        }
        G[0] += g0*aceleracion[0];
        oldG[0] = g0;
        for (int i = 0; i < TS.inputs.length; i++){
            double  g = TS.inputs[i] * g0;
            if(g*oldG[i+1] > 0){
                aceleracion[i+1] *= 1.05;
            }
            else {
                aceleracion[i+1] = 1;
            }       
            G[i+1] += g*aceleracion[i+1];
            oldG[i+1] = g;
        }
    }
    
    private boolean error(double diff){
        return (Math.abs(diff) > 0.5);
    }  

    private double activacion(double x){
        return 1/(1 + Math.exp(-x));
    }
    
    private double propagacion(){
        double result = W[0];

        for (int i = 0; i < TS.inputs.length; i++)
            result += TS.inputs[i] * W[i+1];
        
        return result;
    }
    
    public void cargar (Ejemplo e){
        getEjemplo().copiar(e);
    }
}







