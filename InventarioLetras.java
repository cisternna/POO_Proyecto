public class InventarioLetras{
    
    // Atributos
    private int[] abc = new int[26];    // abcedario (largo 26 letras)
    private int totalCount;             // Suma de los recuentos
    private int nonZeroCount;           // Cuantas letras tienen recuento > 0

    // Constructor
    public InventarioLetras(String data) {
        this.totalCount = 0;        // Los totales comienzan en 0
        this.nonZeroCount = 0;
        data = data.toLowerCase();  // Ignoramos mayusculas

        for(int i = 0; i < data.length(); i++) {
            char letra = data.charAt(i);
            if (letra >= 'a' && letra <= 'z'){  // Verificamos que la letra esta en el abcedario
               int posicion = letra - 'a';      // Posicion en el abcdario ej: 'z' - 'a' = 25
                abc[posicion]++;                // A esa posicion le sumamos 1 recuento
                totalCount++;
            }
        }

        for(int i = 0; i < 26; i++) {   // Recorremos el abc ya calculado
            int letra = abc[i];
            if(letra > 0){
                nonZeroCount++;         // Recuentos > 0
            } 
        }
    }
    
    public char encriptarCesar(char letra) {
        letra = Character.toLowerCase(letra);   // char no es un objeto por lo que usamos un wrapper
        if (!(letra >= 'a' && letra <= 'z')) {  // Comprobamos si esta en el abcedario
            return letra;                       // Si no esta lo mantenemos igual (ej: signos)
        }
        int posicion = letra - 'a';                         // Calculamos la posicion en el abc
        int nuevaPosicion = (posicion + 3) % 26;            // Ciframos y "damos la vuelta" si > 26
        char letraCifrada = (char) (nuevaPosicion + 'a');   // ascii a char
        return letraCifrada;                
    }

    public char desencriptarCesar(char letra) {
        letra = Character.toLowerCase(letra);   // char no es un objeto por lo que usamos un wrapper
        if (!(letra >= 'a' && letra <= 'z')) {  // Comprobamos si esta en el abcedario
            return letra;                       // Si no esta lo mantenemos igual (ej: signos)
        }
        int posicion = letra - 'a';             // Calculamos la posicion en el abc
        int nuevaPosicion = (posicion - 3 + 26) % 26;        
        // Ejemplo: 'a' = 0 --> (0 - 3) --> -3 % 26 = -3, lo cual esta fuera de nuestro abc
        // Sumar 26 no altera el resultado final pero si evita que obtengamos resultados negativos
        // Solucion: 'a' = 0 --> (0 - 3 + 26) --> 23 % 26 = 23, asi "damos la vuelta" al abc
        char letraCifrada = (char) (nuevaPosicion + 'a');   // ascii a char
        return letraCifrada; 
    }

    public String encriptarPalabra(String palabra, int desplazamiento) {
        // Construimos la palabra encriptada agregando las letras una por una
        StringBuilder palabraEncriptada = new StringBuilder("");
        
        for(int i = 0; i < palabra.length(); i++) {
            char letra = palabra.charAt(i); // Reconocemos la letra
            // Encriptamos llamando al metodo correspondiente y agregamos al StringBuilder
            palabraEncriptada.append(encriptarCesar(letra));    
        }
        // toString() necesario para que el dato a retornar sea tipo String
        return palabraEncriptada.toString();
    }

    public String desencriptarPalabra(String palabra, int desplazamiento) {
        // Construimos la palabra desencriptada agregando las letras una por una
        StringBuilder palabraDesencriptada = new StringBuilder("");
        
        for(int i = 0; i < palabra.length(); i++) {
            char letra = palabra.charAt(i); // Reconocemos la letra
            // Desencriptamos llamando al metodo correspondiente y agregamos al StringBuilder
            palabraDesencriptada.append(desencriptarCesar(letra)); 
        }
        // toString() necesario para que el dato a retornar sea tipo String
        return palabraDesencriptada.toString();
    }

    public int get(char letra) {
        letra = Character.toLowerCase(letra);   // char no es un objeto por lo que usamos un wrapper
        if (!(letra >= 'a' && letra <= 'z')) {  // Comprobamos si esta en el abcedario
            throw new IllegalArgumentException("Caracter no valido!"); // Excepcion si no esta
        }
        
        int posicion = letra - 'a'; // Calculamos la posicion de la letra en el abcdario
        return abc[posicion];   // retornamos el total de recuentos

    }

    public void set(char letra, int valor) {
        letra = Character.toLowerCase(letra);   // char no es un objeto por lo que usamos un wrapper
        if (!(letra >= 'a' && letra <= 'z')) {  // Comprobamos si esta en el abcedario
            throw new IllegalArgumentException("Caracter no valido!"); // Excepcion si no esta
        }
        if (valor < 0) { // Si el valor es negativo arrojamos una excepcion
            throw new IllegalArgumentException("Caracter no valido!");
        }

        int indice = letra - 'a';           // Indice de la letra
        int valorAnterior = abc[indice];    // Guardamos el valor anterior de la letra
        
        totalCount = totalCount - valorAnterior + valor; // Actualizamos totalCount
        
        // En caso de que la letra sumara al contador pero ahora su valor sea 0
        if (valorAnterior > 0 && valor == 0) {  
            nonZeroCount--;
        }
        // En caso de que la letra no sumara al contador pero ahora su valor es mayor a 0
        if (valorAnterior == 0 && valor > 0) {
            nonZeroCount++;
        }

        abc[indice] = valor;    // Actualizamos el valor en abc

    }

    public int size() {
        return totalCount;
    }

    public boolean isEmpty() {
        return nonZeroCount == 0;
    }

    @Override   // Sobreescribimos toString() default
    public String toString() {
        // Construimos el resultado (retorno) agregando las letras a un StringBuilder
        StringBuilder resultado = new StringBuilder("[");
        
        for(int i = 0; i < abc.length; i++) {   // Recorremos el abc
            char letraActual = (char) ('a' + i); // Reconocemos la letra
            
            // Agregamos las letras en su totalidad al resultado
            for(int j = 0; j < abc[i] ; j++) { // abc[i] = cantidad de letras de esa letra
                resultado.append(letraActual);
            }
        }
        resultado.append("]");
        // toString() necesario para que el dato a retornar sea tipo String
        return resultado.toString();

    }

    public InventarioLetras add(InventarioLetras otro) {
        return null;
    }

    public InventarioLetras amplifies(int n) {
        return null;
    }

    public InventarioLetras subtract(InventarioLetras otro) {
        return null;
    }
}