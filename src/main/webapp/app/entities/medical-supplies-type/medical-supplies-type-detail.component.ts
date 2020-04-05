import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMedicalSuppliesType } from 'app/shared/model/medical-supplies-type.model';

@Component({
  selector: 'jhi-medical-supplies-type-detail',
  templateUrl: './medical-supplies-type-detail.component.html'
})
export class MedicalSuppliesTypeDetailComponent implements OnInit {
  medicalSuppliesType: IMedicalSuppliesType | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ medicalSuppliesType }) => (this.medicalSuppliesType = medicalSuppliesType));
  }

  previousState(): void {
    window.history.back();
  }
}
