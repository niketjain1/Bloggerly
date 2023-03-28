import React from 'react'
import { useState, useEffect } from 'react';
import { Modal } from 'react-bootstrap';
import { useNavigate } from 'react-router-dom';

function WelcomePage() {

    const navigate = useNavigate()

    const [showModal, setShowModal] = useState(true);
    useEffect(() => {
        const timer = setTimeout(() => {
          setShowModal(false);
          navigate("/")

        }, 3000);
        return () => clearTimeout(timer);
      }, []);
      
      return (
        <div>
          <Modal show={showModal} onHide={() => setShowModal(false)} backdrop="static">
            <Modal.Body>
              <h1>Welcome to Bloggerly</h1>
              <p>Thank you for visiting. Please enjoy your stay.</p>
            </Modal.Body>
          </Modal>      
        </div>
      );
      
}

export default WelcomePage