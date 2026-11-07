public class RutaCorta {

    public static void main(String[] args) {
        int n = 18;               
        int INF = 999999;
        int[][] g = new int[n][n];  

        
        int[][] aristas = {
            {16, 7, 61}, {16, 12, 85}, {10, 7, 89}, {10, 11, 34},
            {7, 1, 85},  {11, 1, 60},  {12, 11, 93}, {12, 1, 82},
            {14, 10, 65}, {14, 15, 69}, {15, 12, 80}, {15, 1, 92},
            {1, 2, 79},  {1, 3, 75},   {3, 5, 73},  {2, 5, 55},
            {15, 2, 99}, {15, 6, 40},  {6, 5, 99},  {15, 13, 64},
            {13, 17, 88}, {17, 6, 79}
        };
        for (int[] a : aristas) {
            g[a[0]][a[1]] = a[2];
            g[a[1]][a[0]] = a[2];
        }

        int origen = 1, destino = 14;

        int[] dist = new int[n];       
        int[] previo = new int[n];      
        boolean[] visitado = new boolean[n];

        for (int i = 0; i < n; i++) {
            dist[i] = INF;
            previo[i] = -1;
        }
        dist[origen] = 0;

        // Dijkstra
        for (int paso = 1; paso < n; paso++) {
           
            int a = -1;
            for (int i = 1; i < n; i++) {
                if (!visitado[i] && (a == -1 || dist[i] < dist[a])) {
                    a = i;
                }
            }
            if (a == -1 || dist[a] == INF) break;
            visitado[a] = true;

           
            for (int b = 1; b < n; b++) {
                if (g[a][b] > 0 && dist[a] + g[a][b] < dist[b]) {
                    dist[b] = dist[a] + g[a][b];
                    previo[b] = a;
                }
            }
        }

        
        String camino = "" + destino;
        int actual = previo[destino];
        while (actual != -1) {
            camino = actual + " - " + camino;
            actual = previo[actual];
        }

        System.out.println("Camino mas corto de " + origen + " a " + destino + ": " + camino);
        System.out.println("Distancia total: " + dist[destino]);
    }
}