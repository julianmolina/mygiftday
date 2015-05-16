<?php

class EventType extends \Phalcon\Mvc\Model
{

    /**
     *
     * @var integer
     */
    public $id_event_type;

    /**
     *
     * @var string
     */
    public $event_type;

    /**
     *
     * @var integer
     */
    public $status;

    /**
     *
     * @var string
     */
    public $update;

    /**
     *
     * @var string
     */
    public $register_date;

    /**
     * Initialize method for model.
     */
    public function initialize()
    {
        $this->hasMany('id_event_type', 'Event', 'id_event_type', NULL);
    }

}
