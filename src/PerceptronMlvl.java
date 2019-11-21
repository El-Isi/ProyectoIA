import java.util.Arrays;

public class PerceptronMlvl implements ANNS {
    class Capa implements ANNS {

        private Neurona[] neurons;

        public Capa(int ns, int inputs) {
            neurons = new Neurona[ns];
            for (int i = 0; i < ns; i++) {
                neurons[i] = new Neurona(inputs);
            }
        }

        public void cargar(Ejemplo e) {
            for (int i = 0; i < neurons.length; i++) {
                neurons[i].cargar(e);
            }
        }

        public void correr() {
            for (int i = 0; i < neurons.length; i++) {
                neurons[i].correr();
            }
        }

        public boolean[] dispara() {
            boolean[] disparos = new boolean[neurons.length];
            for (int i = 0; i < neurons.length; i++) {
                disparos[i] = neurons[i].probar();
            }
            return disparos;
        }

        public void aprender() {
            for (int i = 0; i < neurons.length; i++) {
                System.out.print("\tN" + i + ": ");
                neurons[i].aprender();
                System.out.print(".");
            }
        }

        private void recibeBP(double[] bp) {
            for (int i = 0; i < neurons.length; i++) {
                neurons[i].set_diff(bp[i]);
            }
        }

        public double[] backPropagation() {
            int n = neurons[0].getEjemplo().inputs.length;
            double[] bp = new double[n];
            for (int i = 0; i < neurons.length; i++) {
                double[] foo = neurons[i].backPropagation();
                for (int j = 0; j < bp.length; j++) {
                    bp[j] += foo[j];
                }
            }
            return bp;
        }

        public double actualizar() {
            double eqm = 0;
            for (int i = 0; i < neurons.length; i++) {
                eqm += neurons[i].actualizar();
            }
            return eqm;
        }

        private Ejemplo salida() {
            Ejemplo e = new Ejemplo(neurons.length);
            for (int i = 0; i < neurons.length; i++) {
                e.inputs[i] = neurons[i].getEjemplo().output;
            }
            e.expected = neurons[0].getEjemplo().expected;
            return e;
        }
    }

    private Capa[] lvls;

    public PerceptronMlvl() {
        /*Defaults to Simple Perceptron, 2 inputs*/
        lvls = new Capa[1];
        lvls[0] = new Capa(1, 3);
    }

    public PerceptronMlvl(int[] neuronas, int inputs) {
        lvls = new Capa[neuronas.length];
        for (int i = 0; i < neuronas.length; i++) {
            lvls[i] = new Capa(neuronas[i], inputs);
            inputs = neuronas[i];
        }
    }

    public void cargar(Ejemplo e) {
        lvls[0].cargar(e);
        // Queda pendiente lvls[i].cargar()
        // para toda i > 0
    }

    public void correr() {
        lvls[0].correr();
        for (int i = 1; i < lvls.length; i++) {
            lvls[i].cargar(lvls[i - 1].salida());
            lvls[i].correr();
        }
    }

    public boolean probar() {
        boolean[] disparos = lvls[1].dispara();
        int trues = 0;
        for (boolean disparo : disparos) {
            if (disparo) {
                trues++;
            }
        }
        return trues >= 1;
    }

    public void aprender(){
        for (int i = lvls.length - 1; i > 0; i--){
            System.out.print("C"+i+": ");
            lvls[i].aprender();
            lvls[i-1].recibeBP(lvls[i].backPropagation());
        }
        System.out.print("C0: ");
        lvls[0].aprender();
    }
    public double actualizar(){
        double eqm = 0;
        for (int i = 0; i < lvls.length; i++){
            eqm = lvls[i].actualizar();
        }
        return eqm;
    }
}



















