<?php

use Phinx\Migration\AbstractMigration;

class ProgramBase extends AbstractMigration
{
    /**
     * Change Method.
     *
     * More information on this method is available here:
     * http://docs.phinx.org/en/latest/migrations.html#the-change-method
     *
     * Uncomment this method if you would like to use it.
     *
    public function change()
    {
    }
    */
    
    /**
     * Migrate Up.
     */
    public function up()
    {
       $program = $this->table('program');
       $program->addColumn('name','text' , array('limit'=>100));
       $program->addColumn('description','text' );
       $program->save();
    }

    /**
     * Migrate Down.
     */
    public function down()
    {
       $this->dropTable('program');
    }
}