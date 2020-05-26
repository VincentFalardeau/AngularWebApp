select 
m.idMark as idMark, m.idClass as idClass, m.idCategory as idCategory, 
m.description as markDescription, m.mark as mark, m.ponderation as ponderation, 
ca.description as categoryDescription,
cl.code as classCode, cl.semester as semester, cl.description as classDescription, cl.credits as classCredits
from Mark m 
inner join Category ca on ca.idCategory = m.idCategory
inner join Class cl on cl.idClass = m.idClass