import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Data } from '@angular/router';

import { MedicalDeviceMngTestModule } from '../../../test.module';
import { MedicalSuppliesComponent } from 'app/entities/medical-supplies/medical-supplies.component';
import { MedicalSuppliesService } from 'app/entities/medical-supplies/medical-supplies.service';
import { MedicalSupplies } from 'app/shared/model/medical-supplies.model';

describe('Component Tests', () => {
  describe('MedicalSupplies Management Component', () => {
    let comp: MedicalSuppliesComponent;
    let fixture: ComponentFixture<MedicalSuppliesComponent>;
    let service: MedicalSuppliesService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MedicalDeviceMngTestModule],
        declarations: [MedicalSuppliesComponent],
        providers: [
          {
            provide: ActivatedRoute,
            useValue: {
              data: {
                subscribe: (fn: (value: Data) => void) =>
                  fn({
                    pagingParams: {
                      predicate: 'id',
                      reverse: false,
                      page: 0
                    }
                  })
              }
            }
          }
        ]
      })
        .overrideTemplate(MedicalSuppliesComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MedicalSuppliesComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MedicalSuppliesService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new MedicalSupplies(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.medicalSupplies && comp.medicalSupplies[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });

    it('should load a page', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new MedicalSupplies(123)],
            headers
          })
        )
      );

      // WHEN
      comp.loadPage(1);

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.medicalSupplies && comp.medicalSupplies[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });

    it('should calculate the sort attribute for an id', () => {
      // WHEN
      comp.ngOnInit();
      const result = comp.sort();

      // THEN
      expect(result).toEqual(['id,desc']);
    });

    it('should calculate the sort attribute for a non-id attribute', () => {
      // INIT
      comp.ngOnInit();

      // GIVEN
      comp.predicate = 'name';

      // WHEN
      const result = comp.sort();

      // THEN
      expect(result).toEqual(['name,desc', 'id']);
    });
  });
});
