import {HttpStatusCode} from "@angular/common/http";

export class GenericResponse {
  data: Object = new Object;
  success: boolean = false;
  message: String = '';
  status: HttpStatusCode = HttpStatusCode.Ok;
  title: string = '';
}

export interface ApiResponse<T>{
  data: T;
  success: boolean;
  message: string;
  status: HttpStatusCode;
  title: string;
}