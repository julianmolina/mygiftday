<?php

/**
 * 
 */
class IndexController extends ControllerBase
{
    public function indexAction()
    {
		$array = array();
		foreach (Bonus::find('amount = 5000') as $item) { 
			$array[] = array(
				'amount' => $item->amount
			);
		}
		echo json_encode($array);
    }
	
	public function eventosAction()
	{
		$array = array();
		foreach (EventType::find() as $item) { 
			$array[] = array(
				'id_event_type' => $item->id_event_type,
				'event_type' => $item->event_type
			);
		}
		echo json_encode($array);
	}

	public function InsertAction()
	{
		$user = new User();
		$info = array();
		$info = $this->request->getJsonRawBody();
		
		$checkUser = User::findFirst("id_facebook = ".$info->id_facebook);
		if ($checkUser == false) {
			$user->id_facebook 	= $info->id_facebook;
			$user->name			= $info->name;
			$user->last			= $info->last;
			$user->email		= $info->email;
			$user->birthday		= $info->birthday;
			$user->status		= 1;
			$user->update		= date("Y-m-d H:m:s");
			$user->register_date= date("Y-m-d H:m:s");
			
			if ($user->save() == false) {
				foreach ($user->getMessages() as $item) {
					echo json_encode($item->getMessage());
				}
			} else {
				echo "1";
			}
		} else {
			echo "1";
		}
	}
	
	
	
	public function InserteventAction()
	{
		$event = new Event();
		$info = array();
		$info = $this->request->getJsonRawBody();
		
		$checkUser = Event::findFirst("id_event_type = ".$info->id_event_type . "and id_user = ".$info->id_user . "and date = '".$info->date."'");
		if ($checkUser == false) {
			$contribution->id_event_type 	= $info->id_event_type;
			$contribution->id_user 			= $info->id_user;
			$contribution->id_product		= $info->id_product;
			$contribution->url				= $info->url;
			$contribution->iwant			= $info->iwant;
			$contribution->date				= $info->date;
			$contribution->amount			= $info->amount;
			$contribution->value_product	= $info->value_product;
			$contribution->first_amount		= $info->first_amount;
			$contribution->last_date		= $info->last_date;
			$contribution->status			= 1;
			$contribution->register_date	= date("Y-m-d H:m:s");
			$contribution->update			= date("Y-m-d H:m:s");
			
			if ($contribution->save() == false) {
				foreach ($user->getMessages() as $item) {
					echo json_encode($item->getMessage());
				}
			} else {
				echo "1";
			}
		} else {
			echo "1";
		}
	}		
	
	
	
	
	public function InsertcontributionAction()
	{
		$contribution = new Contribution();
		$info = array();
		$info = $this->request->getJsonRawBody();
		
		$contribution->id_user 	= $info->id_user;
		$contribution->id_event			= $info->id_event;
		$contribution->id_bonus			= $info->id_bonus;
		$contribution->date		= $info->date;
		$contribution->status		= 1;
		$contribution->update		= date("Y-m-d H:m:s");
		$contribution->register_date= date("Y-m-d H:m:s");
			
		if ($contribution->save() == false) {
			foreach ($user->getMessages() as $item) {
				echo json_encode($item->getMessage());
			}
		} else {
			echo "1";
		}
	}	
	
	
	public function UpdatecontributionAction()
	{

		$info = array();
		$info = $this->request->getJsonRawBody();
		$contribution = Contribution::findFirst($info->id_contribution);
		$contribution->message 	= $info->message;
			
		if ($contribution->save() == false) {
			foreach ($user->getMessages() as $item) {
				echo json_encode($item->getMessage());
			}
		} else {
			echo "1";
		}
	}
	
	
	
}
