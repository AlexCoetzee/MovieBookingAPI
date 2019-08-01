import { TestBed } from '@angular/core/testing';

import { TheatreService } from './theatre.service';
import { CinemaService } from './cinema.service';

describe('CinemaService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: CinemaService = TestBed.get(TheatreService);
    expect(service).toBeTruthy();
  });
});
