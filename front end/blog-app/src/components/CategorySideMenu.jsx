import React, { useState, useEffect } from 'react'
import { Link } from 'react-router-dom'
import { toast } from 'react-toastify'
import { ListGroup, ListGroupItem } from 'reactstrap'
import { loadAllCategories } from '../services/Category-service'
import '../Form.css'

function CategorySideMenu() {

  const [categories, setCategories] = useState([])

  useEffect(() => {
    loadAllCategories().then(data => {
      setCategories([...data])
      // console.log(data)
    }).catch(error => {
      console.log(error)
      toast.error("Couldn't load categories !")
    })
  }, [])

  return (
    <div className='form-page'>
      <h3 className='text-center mt-3'>Categories</h3>
      <ListGroup>
        <ListGroupItem tag={Link} to="/" action={true} className='border-0'>
          All Blogs
        </ListGroupItem>
        
        {
          categories && categories.map((cat, index) => {
            return (
              <ListGroupItem tag={Link} to={'/categories/' + cat.id} className="border-0 shadow-0 mt-1" key={index} action={true}>
                {cat.categoryTitle}
              </ListGroupItem>
            )
          })
        }
      </ListGroup>
    </div>
  )
}

export default CategorySideMenu