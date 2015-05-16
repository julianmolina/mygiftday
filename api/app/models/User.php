<?php

use Phalcon\Mvc\Model\Validator\Email as Email;

class User extends \Phalcon\Mvc\Model
{

    /**
     *
     * @var integer
     */
    public $id_user;

    /**
     *
     * @var integer
     */
    public $id_facebook;

    /**
     *
     * @var string
     */
    public $name;

    /**
     *
     * @var string
     */
    public $last;

    /**
     *
     * @var string
     */
    public $email;

    /**
     *
     * @var string
     */
    public $birthday;

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
     * Validations and business logic
     */
    public function validation()
    {

        $this->validate(
            new Email(
                array(
                    'field'    => 'email',
                    'required' => true,
                )
            )
        );
        if ($this->validationHasFailed() == true) {
            return false;
        }
    }

    /**
     * Initialize method for model.
     */
    public function initialize()
    {
        $this->hasMany('id_user', 'Contribution', 'id_user', NULL);
        $this->hasMany('id_user', 'Event', 'id_user', NULL);
    }

}
