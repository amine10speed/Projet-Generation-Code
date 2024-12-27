import { Component, OnInit } from '@angular/core';
import Typed from 'typed.js';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor(private router: Router) {}

  ngOnInit(): void {
    const options = {
      strings: ['With <b>CodeNow</b>, generate your code easily.', 'Respecting <b>design patterns</b>.'],
      typeSpeed: 50,
      backSpeed: 25,
      loop: true,
      showCursor: false, // Disable the Typed.js cursor
    };
    

    const typed = new Typed('.typing-animation', options);
  }

  onTryNow(): void {
    this.router.navigate(['/login']); // Navigate to the dashboard page
  }
}
