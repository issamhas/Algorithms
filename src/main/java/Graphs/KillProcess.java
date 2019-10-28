package Graphs;
import java.util.*;

public class KillProcess {
    public List<Integer> killProcess(List<Integer> pid, List<Integer> ppid, int process){
        // Construct a graph representing the process dependencies
        Map<Integer, List<Integer>> processDependencyGraph = constructProcessGraph(pid,ppid);
        // Traverse the graph and ‘kill’ all processes
        return traverseGraphDFS(processDependencyGraph, process);
    }

    public Map<Integer, List<Integer>> constructProcessGraph(List<Integer> pid, List<Integer> ppid){
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for(int i=0; i<pid.size(); i++){
            int processId = pid.get(i);
            int parentId = ppid.get(i);

            // Update key to have new value
            graph.putIfAbsent(parentId, new ArrayList<>());
            graph.get(parentId).add(processId);
        }
        return graph;
    }

    public List<Integer> traverseGraphDFS(Map<Integer, List<Integer>> graph, int process){
        List<Integer> result = new ArrayList<>();
        traverseGraphDFSHelper(graph, process,result);
        return result;
    }
    public void traverseGraphDFSHelper(Map<Integer, List<Integer>> graph, int process, List<Integer> result){
        result.add(process);
        if(!graph.containsKey(process)) return;
        for(Integer node: graph.get(process)){
            traverseGraphDFSHelper(graph, node, result);
        }
    }

    // Alternative method to collect all the nodes in that
    public List<Integer> traverseGraphBFS(Map<Integer, List<Integer>> graph, int process){
        List<Integer> result = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        result.add(process);
        queue.add(process);

        while(!queue.isEmpty()){
            int currentNode =  queue.remove();
            result.add(currentNode);

            // Don't process the leaf nodes
            if(!graph.containsKey(currentNode)) queue.addAll(result);
        }
        return result;
    }

}
