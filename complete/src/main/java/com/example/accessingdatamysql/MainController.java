package com.example.accessingdatamysql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller	// This means that this class is a Controller
@RequestMapping(path="/demo") // This means URL's start with /demo (after Application path)
public class MainController {
	@Autowired
	private DependencyRepository dependencyRepository;

	@PostMapping(path="/add") // Map ONLY POST Requests
	public @ResponseBody String addNewDependency (@RequestParam String groupId
			, @RequestParam String artifactId, @RequestParam String version
			, @RequestParam String notes) {

		Dependency n = new Dependency();

		Integer id = dependencyRepository.getLastId();
		if(id==null){
			n.setId(1);
		}else {
			id++;
			n.setId(id);
		}

		n.setGroupId(groupId);
		n.setArtifactId(artifactId);
		n.setVersion(version);
		n.setNotes(notes);
		dependencyRepository.save(n);
		return artifactId+" (id="+id+")"+" Saved";
	}

	@PostMapping(path="/delById")
	public @ResponseBody String deleteDependencyById(@RequestParam Integer id){
		dependencyRepository.deleteById(id);
		String artifactId = dependencyRepository.getArtifactIdById(id);
		return artifactId+" (id="+id+")"+" Deleted";
	}

	@PostMapping(path="/delByArtifactId")
	public @ResponseBody String deleteDependencyByArtifactId(@RequestParam String artifactId){
		Integer id = dependencyRepository.getIdByArtifact(artifactId);
		dependencyRepository.deleteById(id);

		return artifactId+" (id="+id+")"+" Deleted";
	}

	@GetMapping(path="/all")
	public @ResponseBody Iterable<Dependency> getAllUsers() {
		// This returns a JSON or XML with the users
		return dependencyRepository.findAll();
	}
}
