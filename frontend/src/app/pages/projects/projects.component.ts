import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import { ProjectService } from '../../services/project.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-projects',
  templateUrl: './projects.component.html',
  styleUrls: ['./projects.component.css']
})
export class ProjectsComponent implements OnInit {
  projects: any[] = [];
  pageSize = 4;
  pageIndex = 0;
  searchTerm: string = '';
  newProjectName: string = '';
  isCreatingNewProject: boolean = false; // Toggle for create form
  userId: number | null = null;

  constructor(
    private authService: AuthService,
    private projectService: ProjectService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.authService.getCurrentUser().subscribe(
      (user) => {
        this.userId = user.id; // Get user ID
        this.fetchProjects();
      },
      (error) => {
        console.error('Error fetching user info:', error);
      }
    );
  }

  fetchProjects(): void {
    if (this.userId === null) return;
    this.projectService.getProjects(this.userId).subscribe(
      (response: any[]) => {
        this.projects = response;
      },
      (error) => {
        console.error('Error fetching projects:', error);
      }
    );
  }

  startCreatingNewProject(): void {
    this.isCreatingNewProject = true; // Enter create mode
    this.newProjectName = ''; // Reset project name
  }

  createNewProject(): void {
    if (this.newProjectName.trim() && this.userId) {
      this.projectService.createProject(this.userId, this.newProjectName).subscribe(
        (newProject) => {
          this.projects.push(newProject); // Add the new project
          this.isCreatingNewProject = false; // Exit create mode
        },
        (error) => {
          console.error('Error creating project:', error);
        }
      );
    }
  }

  cancelCreatingNewProject(): void {
    this.isCreatingNewProject = false; // Exit create mode
    this.newProjectName = ''; // Clear the input
  }

  get filteredProjectsCount() {
    return this.projects.filter((project) => {
      const projectName = project.projectName || '';
      const search = this.searchTerm || '';
      return projectName.toLowerCase().includes(search.toLowerCase());
    }).length; // Calculate filtered count
  }

  filteredProjects() {
    const filtered = this.projects.filter((project) => {
      const projectName = project.projectName || '';
      const search = this.searchTerm || '';
      return projectName.toLowerCase().includes(search.toLowerCase());
    });

    return filtered.slice(this.pageIndex * this.pageSize, (this.pageIndex + 1) * this.pageSize);
  }

  pageChanged(event: any) {
    this.pageIndex = event.pageIndex;
  }

  deleteProject(projectId: number): void {
    if (confirm('Are you sure you want to delete this project?')) {
      this.projectService.deleteProject(projectId).subscribe(
        () => {
          // Remove the deleted project from the list
          this.projects = this.projects.filter((project) => project.id !== projectId);
        },
        (error) => {
          console.error('Error deleting project:', error);
        }
      );
    }
  }

  navigateToProject(projectId: number, projectName: string): void {
    this.router.navigate([`/gen-code`, projectId, projectName]);
  }
  
}
