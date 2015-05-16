<?php

class Event extends \Phalcon\Mvc\Model
{

    /**
     *
     * @var integer
     */
    public $id_event;

    /**
     *
     * @var integer
     */
    public $id_event_type;

    /**
     *
     * @var integer
     */
    public $id_user;

    /**
     *
     * @var integer
     */
    public $id_product;

    /**
     *
     * @var string
     */
    public $url;

    /**
     *
     * @var string
     */
    public $iwant;

    /**
     *
     * @var string
     */
    public $date;

    /**
     *
     * @var string
     */
    public $amount;

    /**
     *
     * @var string
     */
    public $value_product;

    /**
     *
     * @var string
     */
    public $first_amount;

    /**
     *
     * @var string
     */
    public $last_date;

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
        $this->hasMany('id_event', 'Contribution', 'id_event', NULL);
        $this->belongsTo('id_event_type', 'EventType', 'id_event_type', array('foreignKey' => true));
        $this->belongsTo('id_product', 'Product', 'id_product', array('foreignKey' => true));
        $this->belongsTo('id_user', 'User', 'id_user', array('foreignKey' => true));
    }

}
