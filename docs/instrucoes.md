# Instruções de Uso

Para executar o projeto, siga as etapas abaixo:

1. Verifique a variável 'fileName' na classe Main. Esta variável indica o caminho para acessar as informações do grafo. Atualmente, está definida como "graph.txt". Se houver problemas com esse caminho, altere para o caminho absoluto, por exemplo: C:\...\projeto-grafos\graph.txt.

2. Funções da Classe Graph:

    - `printAdjacencyMatrix()`: Imprime a matriz de adjacência.
    - `isConnected()`: Verifica se o grafo é conexo.
    - `getUnreachableVertices(Vertex sourceVertex)`: Lista as cidades não alcançadas por um vértice de referência.
    - `getReachableVertices(Vertex sourceVertex)`: Lista as cidades alcançadas por um vértice de referência.
    - `visitAllRoadsAndCities(Vertex sourceVertex)`: Fornece recomendações de cidades para visitar a partir de um vértice.
    - `shortestPathsFromSource(Vertex sourceVertex)`: Encontra os caminhos mais curtos de um vértice de origem para todos os outros usando o algoritmo de Dijkstra.
