import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMedicalSupplies } from 'app/shared/model/medical-supplies.model';

@Component({
  selector: 'jhi-medical-supplies-detail',
  templateUrl: './medical-supplies-detail.component.html'
})
export class MedicalSuppliesDetailComponent implements OnInit {
  medicalSupplies: IMedicalSupplies | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ medicalSupplies }) => (this.medicalSupplies = medicalSupplies));
  }

  previousState(): void {
    window.history.back();
  }
}
