package br.net.triangulohackerspace.spaceapi.util;

import java.util.ArrayList;
import java.util.List;

import br.net.triangulohackerspace.spaceapi.domain.Project;

public class ProjectUtil {

	private static final String NAME = "example";
	private static final String GIT = "http://github.com/example";
	
	private ProjectUtil() {
	}

	public static Project createProject() {
		return getProject();
	}

	public static List<Project> createProjectList(int howMany) {
		List<Project> projectList = new ArrayList<>();
		for (int i = 0; i < howMany; i++) {
			projectList.add(getProject());
		}
		return projectList;
	}
	
	public static Project getProject() {
		return new Project(NAME, GIT, SpaceUtil.getSpace());
	}
}
