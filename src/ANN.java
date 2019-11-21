import java.util.Arrays;

public class ANN {

    public static void main(String[] args) {
        System.out.println("Perceptron Multicapa. Caso 01 (Compuerta XOR).");
        
        Ejemplo[] trainning_set = new Ejemplo[9];

        ConvertImage cargar_imagen = new ConvertImage();

        int[][][] pixel = cargar_imagen.LoadImage();
  
        trainning_set[0] = new Ejemplo();
        trainning_set[0].inputs[0] = pixel[0][0][0];
        trainning_set[0].inputs[1] = pixel[0][0][1];
        trainning_set[0].inputs[2] = pixel[0][0][2];
        trainning_set[0].expected = pixel[0][0][3];
        
        trainning_set[1] = new Ejemplo();
        trainning_set[1].inputs[0] = pixel[0][1][0];
        trainning_set[1].inputs[1] = pixel[0][1][1];
        trainning_set[1].inputs[2] = pixel[0][1][2];
        trainning_set[1].expected = pixel[0][1][3];
        
        trainning_set[2] = new Ejemplo();
        trainning_set[2].inputs[0] = pixel[0][2][0];
        trainning_set[2].inputs[1] = pixel[0][2][1];
        trainning_set[2].inputs[2] = pixel[0][2][2];
        trainning_set[2].expected = pixel[0][2][3];
        
        trainning_set[3] = new Ejemplo();
        trainning_set[3].inputs[0] = pixel[1][0][0];
        trainning_set[3].inputs[1] = pixel[1][0][1];
        trainning_set[3].inputs[2] = pixel[1][0][2];
        trainning_set[3].expected = pixel[1][0][3];

        trainning_set[4] = new Ejemplo();
        trainning_set[4].inputs[0] = pixel[1][1][0];
        trainning_set[4].inputs[1] = pixel[1][1][1];
        trainning_set[4].inputs[2] = pixel[1][1][2];
        trainning_set[4].expected = pixel[1][1][3];

        trainning_set[5] = new Ejemplo();
        trainning_set[5].inputs[0] = pixel[1][2][0];
        trainning_set[5].inputs[1] = pixel[1][2][1];
        trainning_set[5].inputs[2] = pixel[1][2][2];
        trainning_set[5].expected = pixel[1][2][3];

        trainning_set[6] = new Ejemplo();
        trainning_set[6].inputs[0] = pixel[2][0][0];
        trainning_set[6].inputs[1] = pixel[2][0][1];
        trainning_set[6].inputs[2] = pixel[2][0][2];
        trainning_set[6].expected = pixel[2][0][3];

        trainning_set[7] = new Ejemplo();
        trainning_set[7].inputs[0] = pixel[2][1][0];
        trainning_set[7].inputs[1] = pixel[2][1][1];
        trainning_set[7].inputs[2] = pixel[2][1][2];
        trainning_set[7].expected = pixel[2][1][3];

        trainning_set[8] = new Ejemplo();
        trainning_set[8].inputs[0] = pixel[2][2][0];
        trainning_set[8].inputs[1] = pixel[2][2][1];
        trainning_set[8].inputs[2] = pixel[2][2][2];
        trainning_set[8].expected = pixel[2][2][3];

        int[] layout = new int[2];
        layout[0] = 3;
        layout[1] = 1;
        PerceptronMlvl perceptron = new PerceptronMlvl(layout, trainning_set[0].inputs.length);
        
        //Ciclo de entrenamiento
        double[] errQM = new double[2];
        errQM[0] = 1; //NUEVO
        errQM[1] = 2; //VIEJO

        //perceptron.setInercia(0.05);
        double delta_err = 0.00001; //10^-4
        delta_err *= (delta_err * delta_err); //(//10^-4)^3 == //10^-12

        for (int x = 0; ((x < 100000) && 
                delta_err < Math.abs(errQM[0] - errQM[1])); x++){
            System.out.println("\nRonda " + (Integer.valueOf(x)).toString() + ": t=" + System.currentTimeMillis());
            for (int i = 0; i < trainning_set.length; i++){
                perceptron.cargar(trainning_set[i]);
                perceptron.correr();
                perceptron.aprender();
            }
            errQM[1] = errQM[0];
            errQM[0] = perceptron.actualizar();
            System.out.print("\n\t otro error " + Arrays.toString(errQM));
            System.out.println("\tErrorQM " + (new Double((errQM[0] - errQM[1]))).toString());
            //Continue???
        }


        String[] images = {
                 "punto1.png",
                 "punto2.png",
                 "punto3.png",
                 "punto4.png",
                 "punto5.png",

        };


        for (String image : images) {

            ConvertImage cargar_imagenes = new ConvertImage();

            pixel = cargar_imagenes.LoadImage("C:\\Users\\el_re\\Desktop\\IA\\" + image);

            trainning_set[0] = new Ejemplo();
            trainning_set[0].inputs[0] = pixel[0][0][0];
            trainning_set[0].inputs[1] = pixel[0][0][1];
            trainning_set[0].inputs[2] = pixel[0][0][2];
            trainning_set[0].expected = pixel[0][0][3];

            trainning_set[1] = new Ejemplo();
            trainning_set[1].inputs[0] = pixel[0][1][0];
            trainning_set[1].inputs[1] = pixel[0][1][1];
            trainning_set[1].inputs[2] = pixel[0][1][2];
            trainning_set[1].expected = pixel[0][1][3];

            trainning_set[2] = new Ejemplo();
            trainning_set[2].inputs[0] = pixel[0][2][0];
            trainning_set[2].inputs[1] = pixel[0][2][1];
            trainning_set[2].inputs[2] = pixel[0][2][2];
            trainning_set[2].expected = pixel[0][2][3];

            trainning_set[3] = new Ejemplo();
            trainning_set[3].inputs[0] = pixel[1][0][0];
            trainning_set[3].inputs[1] = pixel[1][0][1];
            trainning_set[3].inputs[2] = pixel[1][0][2];
            trainning_set[3].expected = pixel[1][0][3];

            trainning_set[4] = new Ejemplo();
            trainning_set[4].inputs[0] = pixel[1][1][0];
            trainning_set[4].inputs[1] = pixel[1][1][1];
            trainning_set[4].inputs[2] = pixel[1][1][2];
            trainning_set[4].expected = pixel[1][1][3];

            trainning_set[5] = new Ejemplo();
            trainning_set[5].inputs[0] = pixel[1][2][0];
            trainning_set[5].inputs[1] = pixel[1][2][1];
            trainning_set[5].inputs[2] = pixel[1][2][2];
            trainning_set[5].expected = pixel[1][2][3];

            trainning_set[6] = new Ejemplo();
            trainning_set[6].inputs[0] = pixel[2][0][0];
            trainning_set[6].inputs[1] = pixel[2][0][1];
            trainning_set[6].inputs[2] = pixel[2][0][2];
            trainning_set[6].expected = pixel[2][0][3];

            trainning_set[7] = new Ejemplo();
            trainning_set[7].inputs[0] = pixel[2][1][0];
            trainning_set[7].inputs[1] = pixel[2][1][1];
            trainning_set[7].inputs[2] = pixel[2][1][2];
            trainning_set[7].expected = pixel[2][1][3];

            trainning_set[8] = new Ejemplo();
            trainning_set[8].inputs[0] = pixel[2][2][0];
            trainning_set[8].inputs[1] = pixel[2][2][1];
            trainning_set[8].inputs[2] = pixel[2][2][2];
            trainning_set[8].expected = pixel[2][2][3];

            boolean[] dispara = new boolean[trainning_set.length];

            for (int j = 0; j < trainning_set.length; j++) {
                perceptron.cargar(trainning_set[j]);
                perceptron.correr();
                dispara[j] = perceptron.probar();

            }

            System.out.println(Arrays.toString(dispara));

            int mandar_archivar = 0;

            for (boolean disparo : dispara) {
                if (disparo) mandar_archivar++;
            }

            if (mandar_archivar >= 8) {
                cargar_imagen.writeImage("C:\\Users\\el_re\\Desktop\\IA\\" + image,"C:\\Users\\el_re\\Desktop\\IA\\output\\" + image);
                System.out.println("esta imagen pasa");

            }

            //Continue???
        }
    }
}