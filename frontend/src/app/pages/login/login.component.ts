import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  username: string = '';
  password: string = '';

  constructor(private router: Router, private authService: AuthService) {}

  onSubmit(): void {
    this.authService.login(this.username, this.password).subscribe(
      (token) => {
        localStorage.setItem('token', token); // Save token
        alert('Login successful!');
        this.router.navigate(['/projects']); // Navigate to the projects page
      },
      (error) => {
        alert('Invalid username or password');
        console.error('Login error:', error);
      }
    );
  }
}
