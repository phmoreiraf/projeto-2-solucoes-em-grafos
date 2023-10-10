package entities;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class Route {
	private List<Vertex> vertices;

	public Route() {
		this.vertices = new ArrayList<>();
	}

	public Route(Route currentRoute) {
		this.vertices = new ArrayList<>(currentRoute.vertices);
	}

	public void addVertex(Vertex vertex) {
		vertices.add(vertex);
	}

	public List<Vertex> getVertices() {
		return vertices;
	}

	@Override
	public String toString() {
		StringJoiner joiner = new StringJoiner(" -> ");
		for (Vertex vertex : vertices) {
			joiner.add(vertex.getCityName());
		}
		return joiner.toString();
	}
}
