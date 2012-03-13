select table_name 
from information_schema.referential_constraints 
where referenced_table_name = 'fotos';

SET FOREIGN_KEY_CHECKS=0;
DROP table borrar;
SET FOREIGN_KEY_CHECKS=1;
drop table parcelas_fotos;

