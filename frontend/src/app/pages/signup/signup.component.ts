import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent {
  username: string = '';
  password: string = '';

  constructor(private router: Router, private authService: AuthService) {}

  onSubmit(): void {
    this.authService.signup(this.username, this.password).subscribe(
      (response) => {
        alert('Sign-up successful! Please log in.');
        this.router.navigate(['/login']); // Navigate to login
      },
      (error) => {
        alert('Error during sign-up. Please try again.');
        console.error('Sign-up error:', error);
      }
    );
  }
}
