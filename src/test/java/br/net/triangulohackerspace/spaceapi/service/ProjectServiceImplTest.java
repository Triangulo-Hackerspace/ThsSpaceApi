package br.net.triangulohackerspace.spaceapi.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import br.net.triangulohackerspace.spaceapi.domain.Project;
import br.net.triangulohackerspace.spaceapi.repository.ProjectRepository;
import br.net.triangulohackerspace.spaceapi.service.exception.AlreadyExistsException;
import br.net.triangulohackerspace.spaceapi.service.impl.ProjectServiceImpl;
import br.net.triangulohackerspace.spaceapi.util.ProjectUtil;

@RunWith(MockitoJUnitRunner.class)
public class ProjectServiceImplTest {

	@Mock
	private ProjectRepository projectRepository;

	private ProjectService projectService;

	@Before
	public void setUp() throws Exception {
		projectService = new ProjectServiceImpl(projectRepository);
	}

	@Test
	public void shouldSaveNewProject_GivenThereDoesNotExistOneWithTheSameId_ThenTheSavedProjectShouldBeReturned()
			throws Exception {
		final Project savedProject = stubRepositoryToReturnProjectOnSave();
		final Project project = ProjectUtil.createProject();
		final Project returnedProject = projectService.save(project);
		// verify repository was called with project
		verify(projectRepository, times(1)).save(project);
		assertEquals("Returned project should come from the repository",
				savedProject, returnedProject);
	}

	@Test
	public void shouldSaveNewProject_GivenThereExistsOneWithTheSameId_ThenTheExceptionShouldBeThrown()
			throws Exception {
		stubRepositoryToReturnExistingProject();
		try {
			final Project project = ProjectUtil.createProject();
			projectService.save(project);
			fail("Expected exception");
		} catch (AlreadyExistsException ignored) {
		}
		verify(projectRepository, never()).save(any(Project.class));
	}

	@Test
	public void shouldListAllProjects_GivenThereExistSome_ThenTheCollectionShouldBeReturned()
			throws Exception {
		stubRepositoryToReturnExistingProjects(1);
		Collection<Project> list = projectService.getList();
		assertNotNull(list);
		assertEquals(1, list.size());
		verify(projectRepository, times(1)).findAll();
	}

	@Test
	public void shouldListAllProjects_GivenThereNoneExist_ThenTheEmptyCollectionShouldBeReturned()
			throws Exception {
		stubRepositoryToReturnExistingProjects(0);
		Collection<Project> list = projectService.getList();
		assertNotNull(list);
		assertTrue(list.isEmpty());
		verify(projectRepository, times(1)).findAll();
	}

	private Project stubRepositoryToReturnProjectOnSave() {
		final Project project = ProjectUtil.createProject();
		when(projectRepository.save(any(Project.class))).thenReturn(project);
		return project;
	}

	private void stubRepositoryToReturnExistingProject() {
		final Project project = ProjectUtil.createProject();
		when(projectRepository.findOne(project.getId())).thenReturn(project);
	}

	private void stubRepositoryToReturnExistingProjects(int howMany) {
		when(projectRepository.findAll()).thenReturn(ProjectUtil.createProjectList(howMany));
	}
	
}
