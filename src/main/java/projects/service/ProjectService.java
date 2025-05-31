/**
 * 
 */
package projects.service;

import projects.dao.ProjectDao;
import projects.entity.Project;

/** this Class is implementing the service layer, remember this is a 3-tier application. In this case the CRUD (create,read,ect) are so simple that this acts mainly as a pss-through from
 * from the input layer to the data layer
 */
public class ProjectService {
	private ProjectDao projectDao = new ProjectDao();
	
	// this Method calls the DAO class to insert a project row

	public Project addProject(Project project) {
		return projectDao.insertProject(project);
		
	}

}
