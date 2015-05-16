<?php

class Product extends \Phalcon\Mvc\Model
{

    /**
     *
     * @var integer
     */
    public $id_product;

    /**
     *
     * @var integer
     */
    public $id_provider;

    /**
     *
     * @var string
     */
    public $product;

    /**
     *
     * @var string
     */
    public $price;

    /**
     *
     * @var string
     */
    public $picture;

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
        $this->hasMany('id_product', 'Event', 'id_product', NULL);
        $this->belongsTo('id_provider', 'Provider', 'id_provider', array('foreignKey' => true));
    }

}
