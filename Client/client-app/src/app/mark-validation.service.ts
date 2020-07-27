import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class MarkValidationService {

  constructor() { }

    //True if markStr is a valid number
    isValid(markStr: string): boolean{
      let mark = Number(markStr);
      return !isNaN(mark) && mark >= 0 && mark <= 100;
    }
}
