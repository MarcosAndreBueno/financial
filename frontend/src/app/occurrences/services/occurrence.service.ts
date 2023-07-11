import { Observable } from "rxjs";
import { Occurrence } from "../model/occurrence";

export abstract class OccurrenceService {
    public abstract onSave(newOccForm: Occurrence): Observable<Occurrence>;

    public abstract list(month: number, year: number): Observable<Occurrence[]>;

    public abstract loadById(id: string): Observable<Occurrence>;

    public abstract onEdit(editOccForm: Partial<Occurrence>, id: number): Observable<Occurrence>;

    public abstract deleteById(id: string): Observable<Occurrence>;
}