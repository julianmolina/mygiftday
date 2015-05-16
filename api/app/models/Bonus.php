<?php

class Bonus extends \Phalcon\Mvc\Model
{

    /**
     *
     * @var integer
     */
    public $id_bonus;

    /**
     *
     * @var string
     */
    public $percentage;

    /**
     *
     * @var string
     */
    public $amount;

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
        $this->hasMany('id_bonus', 'Contribution', 'id_bonus', NULL);
    }

}
