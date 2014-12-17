package br.net.triangulohackerspace.spaceapi.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collection;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import br.net.triangulohackerspace.spaceapi.controller.ProjectController;
import br.net.triangulohackerspace.spaceapi.domain.Project;
import br.net.triangulohackerspace.spaceapi.service.ProjectService;
import br.net.triangulohackerspace.spaceapi.util.ProjectUtil;

@RunWith(MockitoJUnitRunner.class)
public class ProjectControllerTest {

    @Mock
    private ProjectService projectService;

    @Inject
    private ProjectController projectController;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void shouldCreateProject() throws Exception {
        final Project savedProject = stubServiceToReturnStoredProject();
        final Project project = ProjectUtil.createProject();
        Project returnedProject = projectController.createProject(project);
        // verify project was passed to ProjectService
        verify(projectService, times(1)).save(project);
        assertEquals("Returned project should come from the service", savedProject, returnedProject);
    }

    private Project stubServiceToReturnStoredProject() {
        final Project project = ProjectUtil.createProject();
        when(projectService.save(any(Project.class))).thenReturn(project);
        return project;
    }


    @Test
    public void shouldListAllProjects() throws Exception {
        stubServiceToReturnExistingProjects(10);
        Collection<Project> projects = projectController.listProjects();
        assertNotNull(projects);
        assertEquals(10, projects.size());
        // verify project was passed to ProjectService
        verify(projectService, times(1)).getList();
    }

    private void stubServiceToReturnExistingProjects(int howMany) {
        when(projectService.getList()).thenReturn(ProjectUtil.createProjectList(howMany));
    }

}
