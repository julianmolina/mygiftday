<?php

class Provider extends \Phalcon\Mvc\Model
{

    /**
     *
     * @var integer
     */
    public $id_provider;

    /**
     *
     * @var string
     */
    public $provider;

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
        $this->hasMany('id_provider', 'Product', 'id_provider', NULL);
    }

}
