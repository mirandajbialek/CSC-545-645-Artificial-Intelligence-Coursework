package src;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class StateMap {
	Vector<Border> borders;
	Vector<String> states;

	public StateMap() {
		borders = new Vector<Border>();
		states = new Vector<String>();
	}

	public StateMap(String fileName) {
		this.borders = new Vector<Border>();
		this.states = new Vector<String>();
	
		Map<String, Integer> stateIndices = new HashMap<>();
	
		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
			String line;
			int index = 0;
	
			while ((line = br.readLine()) != null) {
				String[] parts = line.split(",");
				String stateName = parts[0];
	
				// Add the state to the StateMap and remember its index
				if (!stateIndices.containsKey(stateName)) {
					this.states.add(stateName);
					stateIndices.put(stateName, index);
				}
	
				// Add borders to the StateMap
				int stateIndex = stateIndices.get(stateName);
				for (int i = 1; i < parts.length; i++) {
					String neighborState = parts[i];
					if (!stateIndices.containsKey(neighborState)) {
						this.states.add(neighborState);
						stateIndices.put(neighborState, this.states.size() - 1);
					}
					int neighborIndex = stateIndices.get(neighborState);
					
					if (!borderContains(new Border(stateIndex, neighborIndex)))
						this.borders.add(new Border(stateIndex, neighborIndex));
				}
	
				index++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean borderContains(Border newBorder){
		for (Border existingBorder : this.borders){
			if (existingBorder.equals(newBorder))
				return true;
		}
		return false;
	}
	
}
