<?php

class Contribution extends \Phalcon\Mvc\Model
{

    /**
     *
     * @var integer
     */
    public $id_contribution;

    /**
     *
     * @var integer
     */
    public $id_user;

    /**
     *
     * @var integer
     */
    public $id_event;

    /**
     *
     * @var integer
     */
    public $id_bonus;

    /**
     *
     * @var string
     */
    public $message;

    /**
     *
     * @var string
     */
    public $date;

    /**
     *
     * @var integer
     */
    public $status;

    /**
     *
     * @var string
     */
    public $register_date;

    /**
     *
     * @var string
     */
    public $update;

    /**
     * Initialize method for model.
     */
    public function initialize()
    {
        $this->belongsTo('id_bonus', 'Bonus', 'id_bonus', array('foreignKey' => true));
        $this->belongsTo('id_event', 'Event', 'id_event', array('foreignKey' => true));
        $this->belongsTo('id_user', 'User', 'id_user', array('foreignKey' => true));
    }

}
