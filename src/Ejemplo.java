public class Ejemplo {
    
    public double[] inputs;
    public double output;
    public double expected;
    
    public Ejemplo(){
        inputs = new double[3];
    }
    public Ejemplo(int n){
        inputs = new double[Math.max(n, 3)];
    }
    
    public boolean copiar(Ejemplo e){
        if (e.inputs.length != inputs.length)
            return false;
        
        for (int i = 0; i < inputs.length; i++){
            inputs[i] = e.inputs[i];
        }
        output = e.output;
        expected = e.expected;
        return true;
    }
}










