import { Component, inject, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ApiService } from '../../services/api.service';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent implements OnInit {
  items: any[] = [];
  private readonly api = inject(ApiService);
  private readonly fb = inject(FormBuilder);
  form!: FormGroup;

  ngOnInit(): void {
    this.form = this.fb.group({
      nome: ['', [Validators.required, Validators.minLength(2)]],
      email: ['', [Validators.required, Validators.email]]
    });
    this.load();
  }

  load(): void {
    this.api.getAlunos().subscribe((data) => (this.items = data));
  }

  submit(): void {
    if (this.form.invalid) return;
    this.api.createAluno(this.form.value).subscribe(() => {
      this.form.reset();
      this.load();
    });
  }
}


